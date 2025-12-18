import { FileText, DollarSign, TrendingUp, Clock, Plus, Minus } from 'lucide-react'
import { PortfolioSummary } from '../types'

interface SummaryCardsProps {
  summary: PortfolioSummary
  onDeposit: () => void
  onWithdraw: () => void
}

function SummaryCards({ summary, onDeposit, onWithdraw }: SummaryCardsProps) {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('es-AR', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 2,
    }).format(value)
  }

  return (
    <div>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-4 mb-4">
        <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
          <div className="flex items-center justify-between mb-2">
            <FileText className="text-gray-600 dark:text-gray-400" size={24} />
          </div>
          <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Dinero Ingresado</p>
          <p className="text-2xl font-bold text-gray-900 dark:text-white">
            {formatCurrency(summary.moneyDeposited)}
          </p>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
          <div className="flex items-center justify-between mb-2">
            <div className="w-8 h-8 bg-green-500 rounded-full flex items-center justify-center">
              <DollarSign className="text-white" size={20} />
            </div>
          </div>
          <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Dinero Líquido</p>
          <p className={`text-2xl font-bold ${summary.liquidMoney < 0 ? 'text-red-500' : 'text-gray-900 dark:text-white'}`}>
            {formatCurrency(summary.liquidMoney)}
          </p>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
          <div className="flex items-center justify-between mb-2">
            <DollarSign className="text-purple-500" size={24} />
          </div>
          <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Dinero Invertido</p>
          <p className="text-2xl font-bold text-gray-900 dark:text-white">
            {formatCurrency(summary.moneyInvested)}
          </p>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
          <div className="flex items-center justify-between mb-2">
            <TrendingUp className="text-green-500" size={24} />
          </div>
          <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Rendimiento</p>
          <p className={`text-2xl font-bold ${summary.performance >= 0 ? 'text-green-500' : 'text-red-500'}`}>
            {summary.performance >= 0 ? '+' : ''}{formatCurrency(summary.performance)}
          </p>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
          <div className="flex items-center justify-between mb-2">
            <Clock className="text-gray-600 dark:text-gray-400" size={24} />
          </div>
          <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">Valor Total</p>
          <p className="text-2xl font-bold text-gray-900 dark:text-white">
            {formatCurrency(summary.totalValue)}
          </p>
          <p className="text-xs text-gray-500 dark:text-gray-500 mt-1">
            {summary.performancePercent >= 0 ? '+' : ''}{summary.performancePercent.toFixed(2)}%
          </p>
        </div>
      </div>

      <div className="flex gap-2 justify-end">
        <button
          onClick={onDeposit}
          className="flex items-center gap-2 px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-lg transition-colors"
        >
          <Plus size={18} />
          Ingresar Dinero
        </button>
        <button
          onClick={onWithdraw}
          className="flex items-center gap-2 px-4 py-2 bg-red-600 hover:bg-red-700 text-white rounded-lg transition-colors"
        >
          <Minus size={18} />
          Retirar Dinero
        </button>
      </div>
    </div>
  )
}

export default SummaryCards

