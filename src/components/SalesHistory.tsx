import { ArrowUp, Edit } from 'lucide-react'
import { SoldAsset } from '../types'

interface SalesHistoryProps {
  soldAssets: SoldAsset[]
}

function SalesHistory({ soldAssets }: SalesHistoryProps) {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('es-AR', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 2,
    }).format(value)
  }

  const formatDate = (dateString: string) => {
    const date = new Date(dateString)
    return date.toLocaleDateString('es-AR', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
    })
  }

  const totalPerformance = soldAssets.reduce((sum, asset) => sum + asset.performance, 0)

  return (
    <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-xl font-bold text-gray-900 dark:text-white">Historial de Ventas</h2>
        <div className="text-right">
          <p className="text-xs text-gray-500 dark:text-gray-400 mb-1">Rendimiento Total</p>
          <p className="text-lg font-bold text-green-500">
            +{formatCurrency(totalPerformance)}
          </p>
        </div>
      </div>

      <div className="space-y-4">
        {soldAssets.map((asset) => (
          <div
            key={asset.id}
            className="border border-gray-200 dark:border-gray-700 rounded-lg p-4 hover:bg-gray-50 dark:hover:bg-gray-700/50 transition-colors"
          >
            <div className="flex justify-between items-start">
              <div className="flex-1">
                <div className="flex items-center gap-2 mb-1">
                  <h3 className="font-bold text-gray-900 dark:text-white">{asset.symbol}</h3>
                  <span className="text-xs text-gray-500 dark:text-gray-400">{asset.type}</span>
                </div>
                <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">{asset.name}</p>
                <p className="text-xs text-gray-500 dark:text-gray-500">
                  {asset.units} unidades - {formatDate(asset.saleDate)}
                </p>
              </div>

              <div className="flex items-center gap-4">
                <div className="text-right">
                  <div className="text-xs text-gray-500 dark:text-gray-400 mb-1">
                    <div>Compra: {formatCurrency(asset.purchasePrice)}</div>
                    <div>Venta: {formatCurrency(asset.salePrice)}</div>
                  </div>
                  <div className="flex items-center gap-1 mt-2 text-green-500">
                    <ArrowUp size={16} />
                    <span className="text-sm font-semibold">
                      +{asset.performancePercent.toFixed(2)}% ({formatCurrency(asset.performance)})
                    </span>
                  </div>
                </div>
                <button className="text-gray-400 hover:text-gray-600 dark:hover:text-gray-300">
                  <Edit size={20} />
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default SalesHistory

