import { useState, useEffect } from 'react'
import { useTheme } from '../contexts/ThemeContext'
import Header from './Header'
import SummaryCards from './SummaryCards'
import MyAssets from './MyAssets'
import PortfolioDistribution from './PortfolioDistribution'
import SalesHistory from './SalesHistory'
import MoneyModal from './MoneyModal'
import { portfolioApi } from '../services/api'
import { PortfolioSummary, Asset, SoldAsset, PortfolioDistribution as Distribution } from '../types'
import { Sun, Moon, RefreshCw } from 'lucide-react'

function Dashboard() {
  const { isDark, toggleTheme } = useTheme()
  const [summary, setSummary] = useState<PortfolioSummary | null>(null)
  const [assets, setAssets] = useState<Asset[]>([])
  const [soldAssets, setSoldAssets] = useState<SoldAsset[]>([])
  const [distribution, setDistribution] = useState<Distribution[]>([])
  const [isMoneyModalOpen, setIsMoneyModalOpen] = useState(false)
  const [moneyModalType, setMoneyModalType] = useState<'deposit' | 'withdraw'>('deposit')
  const [loading, setLoading] = useState(true)
  const [updatingPrices, setUpdatingPrices] = useState(false)

  useEffect(() => {
    loadData()
    // Actualizar precios automáticamente al cargar
    updatePrices()
    
    // Actualizar precios cada 5 minutos
    const interval = setInterval(() => {
      updatePrices()
    }, 5 * 60 * 1000) // 5 minutos

    return () => clearInterval(interval)
  }, [])

  const loadData = async () => {
    try {
      setLoading(true)
      const [summaryData, assetsData, soldAssetsData, distributionData] = await Promise.all([
        portfolioApi.getSummary(),
        portfolioApi.getAssets(),
        portfolioApi.getSoldAssets(),
        portfolioApi.getDistribution(),
      ])
      setSummary(summaryData)
      setAssets(assetsData)
      setSoldAssets(soldAssetsData)
      setDistribution(distributionData)
    } catch (error) {
      console.error('Error loading data:', error)
      // Fallback to mock data if API fails
      setSummary({
        moneyDeposited: 0,
        liquidMoney: -32900,
        moneyInvested: 32900,
        performance: 3520,
        totalValue: 2107.5,
        performancePercent: 0,
      })
      setAssets([
        {
          id: '1',
          symbol: 'MSFT',
          name: 'Microsoft Corp.',
          type: 'Acción',
          units: 30,
          purchasePrice: 280,
          totalPurchase: 8400,
          currentPrice: 325.75,
          totalCurrent: 9772.5,
          performance: 1372.5,
          performancePercent: 16.34,
        },
        {
          id: '2',
          symbol: 'AL30',
          name: 'Bono Argentina 2030',
          type: 'Bono',
          units: 200,
          purchasePrice: 42.5,
          totalPurchase: 8500,
          currentPrice: 45.8,
          totalCurrent: 9160,
          performance: 660,
          performancePercent: 7.76,
        },
        {
          id: '3',
          symbol: 'TSLA',
          name: 'Tesla Inc.',
          type: 'Acción',
          units: 25,
          purchasePrice: 245,
          totalPurchase: 6125,
          currentPrice: 238.2,
          totalCurrent: 5955,
          performance: -170,
          performancePercent: -2.78,
        },
        {
          id: '4',
          symbol: 'T-30',
          name: 'Bono USA Treasury',
          type: 'Bono',
          units: 100,
          purchasePrice: 98.75,
          totalPurchase: 9875,
          currentPrice: 101.2,
          totalCurrent: 10120,
          performance: 245,
          performancePercent: 2.48,
        },
      ])
      setSoldAssets([
        {
          id: '5',
          symbol: 'AAPL',
          name: 'Apple Inc.',
          type: 'Acción',
          units: 50,
          purchasePrice: 150.25,
          salePrice: 178.5,
          saleDate: '2025-12-17',
          performance: 1412.5,
          performancePercent: 18.8,
        },
      ])
      setDistribution([
        { symbol: 'MSFT', value: 9772.5, percentage: 29.9 },
        { symbol: 'TSLA', value: 5955, percentage: 17.0 },
        { symbol: 'AL30', value: 9160, percentage: 26.2 },
        { symbol: 'T-30', value: 10120, percentage: 28.9 },
      ])
    } finally {
      setLoading(false)
    }
  }

  const handleDeposit = () => {
    setMoneyModalType('deposit')
    setIsMoneyModalOpen(true)
  }

  const handleWithdraw = () => {
    setMoneyModalType('withdraw')
    setIsMoneyModalOpen(true)
  }

  const handleMoneySubmit = async (amount: number) => {
    try {
      if (moneyModalType === 'deposit') {
        await portfolioApi.depositMoney(amount)
      } else {
        await portfolioApi.withdrawMoney(amount)
      }
      await loadData()
      setIsMoneyModalOpen(false)
    } catch (error) {
      console.error('Error processing transaction:', error)
    }
  }

  const updatePrices = async () => {
    try {
      setUpdatingPrices(true)
      const updatedSummary = await portfolioApi.updatePrices()
      setSummary(updatedSummary)
      // Recargar todos los datos para obtener los precios actualizados
      await loadData()
    } catch (error) {
      console.error('Error updating prices:', error)
    } finally {
      setUpdatingPrices(false)
    }
  }

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-100 dark:bg-gray-900 flex items-center justify-center">
        <div className="text-gray-600 dark:text-gray-400">Cargando...</div>
      </div>
    )
  }

  return (
    <div className="min-h-screen bg-gray-100 dark:bg-gray-900 transition-colors">
      <div className="container mx-auto px-4 py-8">
        <div className="flex justify-between items-center mb-8">
          <Header />
          <div className="flex gap-2">
            <button
              onClick={updatePrices}
              disabled={updatingPrices}
              className="flex items-center gap-2 px-4 py-2 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white rounded-lg transition-colors"
              title="Actualizar precios"
            >
              <RefreshCw size={18} className={updatingPrices ? 'animate-spin' : ''} />
              {updatingPrices ? 'Actualizando...' : 'Actualizar Precios'}
            </button>
            <button
              onClick={toggleTheme}
              className="p-2 rounded-lg bg-white dark:bg-gray-800 text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors"
              aria-label="Toggle theme"
            >
              {isDark ? <Sun size={20} /> : <Moon size={20} />}
            </button>
          </div>
        </div>

        {summary && <SummaryCards summary={summary} onDeposit={handleDeposit} onWithdraw={handleWithdraw} />}

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mt-8">
          <MyAssets assets={assets} />
          <PortfolioDistribution distribution={distribution} />
        </div>

        <div className="mt-8">
          <SalesHistory soldAssets={soldAssets} />
        </div>
      </div>

      {isMoneyModalOpen && (
        <MoneyModal
          type={moneyModalType}
          onClose={() => setIsMoneyModalOpen(false)}
          onSubmit={handleMoneySubmit}
        />
      )}
    </div>
  )
}

export default Dashboard

